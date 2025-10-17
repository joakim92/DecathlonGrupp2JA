const el = (id) => document.getElementById(id);
const err = el('error');
const msg = el('msg');

function setError(text) { err.textContent = text; }
function setMsg(text) { msg.textContent = text; }

const api = (path) => `/com/example/decathlon/api${path}`;

const modes = {
  dec: {
    id: 'dec',
    events: [
      {id:'Dec_100m', label:'100m (s)'},
      {id:'Dec_LongJump', label:'Long Jump (cm)'},
      {id:'Dec_ShotPut', label:'Shot Put (m)'},
      {id:'Dec_400m', label:'400m (s)'},
      {id:'Dec_110mHurdles', label:'110m Hurdles (s)'},
      {id:'Dec_HighJump', label:'High Jump (cm)'},
      {id:'Dec_PoleVault', label:'Pole Vault (cm)'},
      {id:'Dec_DiscusThrow', label:'Discus Throw (m)'},
      {id:'Dec_JavelinThrow', label:'Javelin Throw (m)'},
      {id:'Dec_1500m', label:'1500m (s)'}
    ]
  },
  hep: {
    id: 'hep',
    events: [
      {id:'Hep_100mHurdles', label:'100m Hurdles (s)'},
      {id:'Hep_HighJump', label:'High Jump (cm)'},
      {id:'Hep_ShotPut', label:'Shot Put (m)'},
      {id:'Hep_200m', label:'200m (s)'},
      {id:'Hep_LongJump', label:'Long Jump (cm)'},
      {id:'Hep_JavelinThrow', label:'Javelin Throw (m)'},
      {id:'Hep_800m', label:'800m (s)'}
    ]
  }
};

let currentMode = 'dec';

function populateEvents() {
  const sel = el('event');
  sel.innerHTML = '';
  modes[currentMode].events.forEach(ev => {
    const o = document.createElement('option');
    o.value = ev.id;
    o.textContent = ev.label;
    sel.appendChild(o);
  });
}

function headerForMode() {
  return modes[currentMode].events.map(e => e.label.split(' (')[0]);
}

function visibleScore(r, displayName) {
  const map = {
    '100m':'Dec_100m',
    'Long Jump':'Dec_LongJump',
    'Shot Put':'Dec_ShotPut',
    '400m':'Dec_400m',
    '110m Hurdles':'Dec_110mHurdles',
    'High Jump':'Dec_HighJump',
    'Pole Vault':'Dec_PoleVault',
    'Discus Throw':'Dec_DiscusThrow',
    'Javelin Throw':'Dec_JavelinThrow',
    '1500m':'Dec_1500m',
    '100m Hurdles':'Hep_100mHurdles',
    '200m':'Hep_200m',
    '800m':'Hep_800m'
  };
  const id = map[displayName] || '';
  return r.scores?.[id] ?? '';
}

el('modeDec').addEventListener('change', async () => {
  currentMode = 'dec';
  populateEvents();
  await renderStandings();
});
el('modeHep').addEventListener('change', async () => {
  currentMode = 'hep';
  populateEvents();
  await renderStandings();
});

el('add').addEventListener('click', async () => {
  const name = el('name').value;
  try {
    const res = await fetch(api('/competitors'), {
      method: 'POST', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name })
    });
    if (!res.ok) {
      const t = await res.text();
      setError(t || 'Failed to add competitor');
    } else {
      setMsg('Added');
      err.textContent = '';
    }
    await renderStandings();
  } catch {
    setError('Network error');
  }
});

el('save').addEventListener('click', async () => {
  const body = {
    name: el('name2').value,
    event: el('event').value,
    raw: parseFloat(el('raw').value)
  };
  try {
    const res = await fetch(api('/score'), {
      method: 'POST', headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    });
    if (!res.ok) {
      setError('Score failed');
      return;
    }
    const json = await res.json();
    setMsg(`Saved: ${json.points} pts`);
    err.textContent = '';
    await renderStandings();
  } catch {
    setError('Score failed');
  }
});

el('export').addEventListener('click', async () => {
  try {
    const res = await fetch(api('/export.csv'));
    const text = await res.text();
    const blob = new Blob([text], { type: 'text/csv;charset=utf-8' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = 'results.csv';
    a.click();
  } catch {
    setError('Export failed');
  }
});

async function renderStandings() {
  try {
    const res = await fetch(api('/standings'));
    if (!res.ok) throw new Error();
    const data = await res.json();
    const cols = headerForMode();
    el('thead').innerHTML = `<tr><th>Name</th>${cols.map(c=>`<th>${c}</th>`).join('')}<th>Total</th></tr>`;
    const rows = data.sort((a,b)=> (b.total||0)-(a.total||0))
      .map(r => {
        const cells = cols.map(c => `<td>${escapeHtml(visibleScore(r, c))}</td>`).join('');
        return `<tr><td>${escapeHtml(r.name)}</td>${cells}<td>${r.total ?? 0}</td></tr>`;
      }).join('');
    el('standings').innerHTML = rows;
  } catch {
    setError('Could not load standings');
  }
}

function escapeHtml(s){
  return String(s).replace(/[&<>"]/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;'}[c]));
}

populateEvents();
renderStandings();
