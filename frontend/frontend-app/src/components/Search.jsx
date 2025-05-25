import React, { useState, useEffect } from 'react';
import '../styles/Search.css';

function Search({ djeca, ids, currentIndex, setCurrentIndex }) {
  const [query, setQuery] = useState('');
  const [filtered, setFiltered] = useState([]);
  const [showSuggestions, setShowSuggestions] = useState(false);

  useEffect(() => {
    setQuery('');
    setFiltered([]);
    setShowSuggestions(false);
  }, [currentIndex]);

  const handleInputChange = (e) => {
    const value = e.target.value;
    setQuery(value);

    if (value.trim() === '') {
      setFiltered([]);
      setShowSuggestions(false);
      return;
    }

    const lower = value.toLowerCase();
    const results = djeca.filter((d) =>
      `${d.id} ${d.ime} ${d.prezime} ${d.oib}`.toLowerCase().includes(lower)
    );

    setFiltered(results);
    setShowSuggestions(true);
  };

  const handleSelect = (id) => {
    const index = ids.findIndex((x) => x === id);
    if (index !== -1) {
      setCurrentIndex(index);
    }
  };

  return (
    <div className="search-container">
      <input
        type="text"
        placeholder="Pretraži po imenu, prezimenu, OIB-u ili ID-u"
        value={query}
        onChange={handleInputChange}
        onBlur={() => setTimeout(() => setShowSuggestions(false), 100)}
        onFocus={() => {
          if (filtered.length > 0) setShowSuggestions(true);
        }}
        className="search-input"
      />
      {showSuggestions && filtered.length > 0 && (
        <ul className="search-suggestions">
          {filtered.slice(0, 5).map((dijete) => (
            <li
              key={dijete.id}
              onClick={() => handleSelect(dijete.id)}
              className="search-suggestion"
              onMouseDown={(e) => e.preventDefault()}
            >
              {dijete.ime} {dijete.prezime} ({dijete.oib}) - ID: {dijete.id}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Search;
