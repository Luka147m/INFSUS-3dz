import '../styles/Navigator.css'
const Navigator = ({ ids, currentIndex, setCurrentIndex }) => {
  return (
    <div className="navigator">
      <button
        className="nav-button nav-prev"
        onClick={() => setCurrentIndex(currentIndex - 1)}
        disabled={currentIndex === 0}
      >
        Prethodno
      </button>

      <span className="nav-status">
        Dijete {currentIndex + 1} od {ids.length}
      </span>

      <button
        className="nav-button nav-next"
        onClick={() => setCurrentIndex(currentIndex + 1)}
        disabled={currentIndex === ids.length - 1}
      >
        Sljedeće
      </button>
    </div>
  );
};

export default Navigator;
