
const Navigator = ({ ids, currentIndex, setCurrentIndex }) => {
  return (
    <div style={{ marginTop: '1rem', textAlign: 'center' }}>
      <button onClick={() => setCurrentIndex(currentIndex - 1)} disabled={currentIndex === 0}>⬅️</button>
      <span style={{ margin: '0 1rem' }}>{currentIndex + 1} / {ids.length}</span>
      <button onClick={() => setCurrentIndex(currentIndex + 1)} disabled={currentIndex === ids.length - 1}>➡️</button>
    </div>
  );
};

export default Navigator;