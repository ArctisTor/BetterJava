import { FilterOption } from "./FilterOption";

export class SearchFilterOptions {
    filters: FilterOption[];

    constructor(filters: FilterOption[]) {
        this.filters = filters
    }
}