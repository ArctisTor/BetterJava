import { useState, useEffect } from "react";
import { getAllVTubers } from '../services/httpService';
import { VTuber } from '../models/VTuber'
import VTuberEntity from './VTuberEntity'

const VTuberList = () => {
  const [vtubers, setVTubers] = useState<VTuber[]>([]);

  const loadVTubers = async () => {
    try {
      let response = await getAllVTubers();
      if (Array.isArray(response)) {
        setVTubers(response);
      }
    } catch (error) {
      console.log("Error fetching data", error);
    }
  };

  useEffect(() => {
    loadVTubers();
  }, []);

  return (<>
        <section className="bg-blue-50 px-4 py-10">
        <div className="container-xl lg:container m-auto">
          <h2 className="text-3xl font-bold text-indigo-500 mb-6 text-center">
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                {vtubers.map((vtuber) => (
                  <VTuberEntity key={vtuber.id} vtuber={vtuber} />
                ))}
              </div>
        </div>
      </section>
  </>)
};

export default VTuberList;
