import { useState, useEffect } from "react";
import { VTuber } from "../models/VTuber";
import VTuberEntity from "./VTuberEntity";
import SearchBar from "../component/SearchBar/SearchBar";
import SearchFilterOption from "../component/SearchFilterOption/SearchFilterOption";
import { FilterOption } from "../models/FilterOption";
import { filterService } from "../services/filterService";
import HttpService from "../services/httpService";

const httpService = new HttpService();

const VTuberList = () => {
  const [vtubers, setVtubers] = useState<VTuber[]>([]);
  const [dropdownOptions, setDropdownOptions] = useState<string[]>([]);

  const handleFilter = (filterOption: FilterOption) => {
    filterService.addFilter(filterOption);
  };

  useEffect(() => {
    const handleVtuberUpdate = (newVtubers: VTuber[]) => {
      setDropdownOptions(Object.keys(newVtubers[0]))
      setVtubers(newVtubers); // Update the state with the latest vtubers
    };

    httpService.subscribeOnVTubers(handleVtuberUpdate)


    httpService.getAllVTubers();

    // Cleanup on unmount
    return () => httpService.unsubscribeOnVTubers(handleVtuberUpdate);
  }, []);

  return (
    <>
      <SearchBar dropdownOption={dropdownOptions} onSearch={handleFilter} />
      <SearchFilterOption />
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
