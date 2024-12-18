import { FilterOption } from "../../models/FilterOption";
import { useState, useEffect } from "react";
import { filterService } from "../../services/filterService";
import "./SearchFilterOption.css";

const SearchFilterOption = () => {
  const [filters, setFilters] = useState<FilterOption[]>([]);

  const handleRemoveFilter = (index: number) => {
    filterService.removeFilter(index);
  };

  // Sync the internal selectedOption state with selectedCategory prop
  useEffect(() => {
    // Update function to sync state with the service
    const updateFilters = (newFilters: FilterOption[]) => {
      setFilters(newFilters); // Get latest filters from the service
    };

    filterService.subscribe(updateFilters);

    // Cleanup on unmount
    return () => filterService.unsubscribe(updateFilters);
  }, []);

  return (
    <>
      <div>
        <h3>Current Filters:</h3>
        {filters.length > 0 ? (
          <div className="filter-container">
            {filters.map((filter, index) => (
              <div key={index} className="filter-box">
                <p className="field">{filter.query}, </p>
                <p className="field">{filter.category}</p>
                <button className="remove-button" onClick={() => handleRemoveFilter(index)}>
                  X
                </button>
              </div>
            ))}
          </div>
        ) : (
          <p>No filters applied.</p>
        )}
      </div>
    </>
  );
};

export default SearchFilterOption;
