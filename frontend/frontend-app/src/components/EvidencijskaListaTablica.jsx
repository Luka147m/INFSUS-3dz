
const EvidencijskaListaTablica = ({ lista, onEdit, onDelete }) => {
  return (
    <table style={{ width: '100%', borderCollapse: 'collapse' }}>
      <thead>
        <tr>
          <th>ID</th>
          <th>Odgojitelj</th>
          <th>Datum</th>
          <th>Prisutan</th>
          <th>Program</th>
          <th>Napomena</th>
          <th>Akcije</th>
        </tr>
      </thead>
      <tbody>
        {lista.map((item) => (
          <tr key={item.idEvidencijskaLista}>
            <td>{item.idEvidencijskaLista}</td>
            <td>{item.idOdgojitelj}</td>
            <td>{item.datum}</td>
            <td>{item.prisutan ? 'Da' : 'Ne'}</td>
            <td>{item.program}</td>
            <td>{item.napomena}</td>
            <td>
              <button onClick={() => onEdit(item)}>✏️ Uredi</button>
              <button onClick={() => onDelete(item.idEvidencijskaLista)} style={{ marginLeft: '0.5rem' }}>🗑️ Obriši</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default EvidencijskaListaTablica;
