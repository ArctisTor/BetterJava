import { VTuber } from "../models/VTuber";


export const getAllVTubers = async(): Promise<VTuber[]> => {
    let vtubers: any[] = []
    try {
        let response = await fetch("/vtuber");
        response = await response.json();
        //@ts-ignore
        vtubers = response.Vtubers as VTuber[]
    } catch (error) {
        console.error('Error fetching VTubers:', error);
    } 
    return vtubers
}