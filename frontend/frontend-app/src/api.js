const handleResponse = async (res) => {
  if (res.status === 204) {
    return [];
  }

  if (res.status === 201) {
    const text = await res.text();
    try {
      return JSON.parse(text);
    } catch {
      return text;
    }
  }

  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
  return res.json();
};

const BASE_URL = 'http://localhost:8080';

export const getDjecaIds = async () => {
  const res = await fetch(`${BASE_URL}/api/djeca/ids`);
  return handleResponse(res);
};

export const getDijeteById = async (id) => {
  const res = await fetch(`${BASE_URL}/api/djeca/dijete/${id}`);
  return handleResponse(res);
};

export const updateDijete = async (dijete) => {
  const res = await fetch(`${BASE_URL}/api/djeca/dijete/${dijete.idDijete}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(dijete),
  });
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};

export const deleteDijeteById = async (id) => {
  const res = await fetch(`${BASE_URL}/api/djeca/dijete/${id}`, {
    method: 'DELETE',
  });
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};

export const createDijete = async (dijete) => {
   const res = await fetch(
    `${BASE_URL}/api/djeca`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dijete),
    }
  );
  if (!res.ok) throw new Error('Neuspješno dodavanje djeteta');
  const message = await res.text();
  return message
};

export const getRoditelji = async () => {
  const res = await fetch(`${BASE_URL}/api/roditelji`);
  return res.json();
};

export const getEvidencijaByDijeteId = async (id) => {
  const res = await fetch(`${BASE_URL}/api/evidencija/dijete/${id}`);
  return handleResponse(res);
};

export const deleteEvidencija = async (id) => {
  const res = await fetch(`${BASE_URL}/api/evidencija/${id}`, {
    method: 'DELETE',
  });
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};

export const updateEvidencija = async (evidencija) => {
  const res = await fetch(
    `${BASE_URL}/api/evidencija/${evidencija.idEvidencijskaLista}`,
    {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(evidencija),
    }
  );
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};

export const createEvidencija = async (evidencija) => {
  const res = await fetch(
    `${BASE_URL}/api/evidencija/dijete/${evidencija.idDijete}`,
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(evidencija),
    }
  );
};

export const createRoditelj = async (roditelj) => {
  const res = await fetch(`${BASE_URL}/api/roditelji`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(roditelj),
  });
  return handleResponse(res);
};

export const deleteRoditelj = async (id) => {
  const res = await fetch(`${BASE_URL}/api/roditelji/roditelj/${id}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (res.status === 204) return;
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};

export const updateRoditelj = async (id, roditelj) => {
  const res = await fetch(`${BASE_URL}/api/roditelji/roditelj/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(roditelj),
  });

  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
  return res.text();
};
