const handleResponse = async (res) => {
  if (res.status === 204) {
    return [];
  }
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
  return res.json();
};

export const getDjecaIds = async () => {
  const res = await fetch('http://localhost:8080/api/djeca/ids');
  return handleResponse(res);
};

export const getDijeteById = async (id) => {
  const res = await fetch(`http://localhost:8080/api/djeca/dijete/${id}`);
  return handleResponse(res);
};

export const getEvidencijaByDijeteId = async (id) => {
  const res = await fetch(`http://localhost:8080/api/evidencija/dijete/${id}`);
  return handleResponse(res);
};

export const deleteEvidencija = async (id) => {
  const res = await fetch(`http://localhost:8080/api/evidencija/${id}`, {
    method: 'DELETE',
  });
  if (!res.ok) {
    const errorText = await res.text();
    throw new Error(`Greška ${res.status}: ${res.statusText} - ${errorText}`);
  }
};
