import { useState } from "react";
import './SearchBar.css'
import SearchBarDropdown from "../SearchBarDropdown/SearchBarDropdown";
import { FilterOption } from "../../models/FilterOption";

interface DropdownProps {
  dropdownOption: string[],
  onSearch: (filterOption: FilterOption) => void
}

const SearchBar: React.FC<DropdownProps>= ({dropdownOption, onSearch}) => {
  const [searchQuery, setSearchQuery] = useState("");
  const [category, setCategory] = useState("Select an option");

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    //event.preventDefault(); //Not needed for text inputs
    setSearchQuery(event.target.value);
  }

  const handleSearch = () => {
    console.log(`Search query: ${searchQuery}`);
    const newFilterOption = new FilterOption(searchQuery, category);
    onSearch(newFilterOption)
    setSearchQuery('')
    setCategory('Select an option')
  };

  const handleSelect = (selected: string) => {
    setCategory(selected)
  }

  const isValidSearch = () => {
    return (
      category.trim().length === 0 || category === 'Select an option' || searchQuery.trim().length === 0
    );
  };
  

  return (
    <>
      <div className="vtuber-search-bar">
        <input
          type="text"
          placeholder="Search..."
          value={searchQuery}
          onChange={handleInputChange}
          className="vtuber-search-bar-input"
        />
        <SearchBarDropdown options={dropdownOption} onSelect={handleSelect} selectedCategory={category}/>
        <button onClick={handleSearch} className="search-button" disabled={isValidSearch()}>
          Search
        </button>
      </div>
    </>
  );
};

export default SearchBar;
