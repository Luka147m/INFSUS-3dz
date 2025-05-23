import { useEffect, useState } from 'react';
import {
  getDjecaIds,
  getDijeteById,
  getEvidencijaByDijeteId,
  deleteEvidencija
} from '../api';
import Navigator from '../components/Navigator';
import EvidencijskaListaTable from '../components/EvidencijskaListaTablica';

const MasterDetailPage = () => {
  const [ids, setIds] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [dijete, setDijete] = useState(null);
  const [evidencija, setEvidencija] = useState([]);

  const loadData = async (id) => {
    let dijeteData = null;
    let evidencijaData = [];
    try {
      dijeteData = await getDijeteById(id);
      setDijete(dijeteData);
    } catch (err) {
      console.error('Greška prilikom dohvaćanja djeteta:', err);
      setDijete(null);
    }
    try {
      evidencijaData = await getEvidencijaByDijeteId(id);
      setEvidencija(evidencijaData);
    } catch (err) {
      console.error('Greška prilikom dohvaćanja evidencije:', err);
      setEvidencija([]);
    }
  };

  useEffect(() => {
    getDjecaIds()
      .then(data => {
        setIds(data);
        if (data.length > 0) {
          loadData(data[0]);
        }
      })
      .catch(err => console.error('Greška prilikom dohvaćanja ID-jeva:', err));
  }, []);

  useEffect(() => {
    if (ids.length > 0) {
      loadData(ids[currentIndex]);
    }
  }, [currentIndex, ids]);

  const handleDelete = async (id) => {
    try {
      await deleteEvidencija(id);
      setEvidencija(prev => prev.filter(el => el.idEvidencijskaLista !== id));
    } catch (err) {
      console.error('Greška pri brisanju:', err);
    }
  };

  if (!dijete) return <p>Učitavanje...</p>;

  return (
    <div style={{ padding: '2rem' }}>
      <h1>Djeca - Pregled i Detalji</h1>
      <Navigator ids={ids} currentIndex={currentIndex} setCurrentIndex={setCurrentIndex} />
      <h2>Dijete #{ids[currentIndex]}</h2>
      <pre>{JSON.stringify(dijete, null, 2)}</pre>

      <h3>Evidencijska lista</h3>
      <EvidencijskaListaTable
        lista={evidencija}
        onEdit={(item) => console.log('Uredi:', item)}
        onDelete={handleDelete}
      />
    </div>
  );
};

export default MasterDetailPage;