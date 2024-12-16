import { useState, useEffect } from "react";
import { getAllVTubers } from "../services/httpService";
import { VTuber } from "../models/VTuber";
import VTuberEntity from "./VTuberEntity";
import SearchBar from "../component/SearchBar/SearchBar";
import SearchFilterOption from "../component/SearchFilterOption/SearchFilterOption";
import { SearchFilterOptions } from "../models/SearchFilterOptions";
import { FilterOption } from "../models/FilterOption";


const VTuberList = () => {
  const [vtubers, setVTubers] = useState<VTuber[]>([]);
  const [dropdownOptions, setDropdownOptions] = useState<string[]>([]);
  const [searchFilterOption, setSearchFilterOptions] = useState<SearchFilterOptions>({filters: []})

  const loadVTubers = async () => {
    try {
      let response = await getAllVTubers();
      if (Array.isArray(response)) {
        setDropdownOptions(Object.keys(response[0]))
        setVTubers(response);
      }
    } catch (error) {
      console.log("Error fetching data", error);
    }
  };

  const handleFilter = (filterOption: FilterOption) => {
    console.log(`Your current filter is: ${filterOption.query} and ${filterOption.category}`)

    setSearchFilterOptions((prevState) => {
      return {
        filters: [...prevState.filters, filterOption], // Add new filter to the existing filters array
      };
    });

  }

  useEffect(() => {
    loadVTubers();
  }, []);

  return (
    <>
      <SearchBar dropdownOption={dropdownOptions} onSearch={handleFilter}/>
      <SearchFilterOption filters={searchFilterOption}/>
      <section className="bg-blue-50 px-4 py-10">
        <div className="container-xl lg:container m-auto">
          <h2 className="text-3xl font-bold text-indigo-500 mb-6 text-center"></h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {vtubers.map((vtuber) => (
              <VTuberEntity key={vtuber.id} vtuber={vtuber} />
            ))}
          </div>
        </div>
      </section>
    </>
  );
};

export default VTuberList;
