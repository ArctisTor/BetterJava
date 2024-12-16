import { SearchFilterOptions } from "../../models/SearchFilterOptions";
import { useState, useEffect } from "react";

const SearchFilterOption: React.FC<{filters: SearchFilterOptions}> = ({ filters }) => {

  const [currentFilters, setCurrentFilter] = useState<SearchFilterOptions>({filters: []})


  // Sync the internal selectedOption state with selectedCategory prop
  useEffect(() => {
    if (filters && filters.filters) { 
      setCurrentFilter(filters);
    }
  }, [filters]);

  return (
    <>
      <div>
        {currentFilters.filters && currentFilters.filters.length > 0 ? (
          currentFilters.filters.map((filter, index) => (
            <div key={index}>
              <p>Field: {filter.query}</p>
              <p>Option: {filter.category}</p>
            </div>
          ))
        ) : (
          <p>No filters available.</p>
        )}
      </div>
    </>
  );
};

export default SearchFilterOption;
