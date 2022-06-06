import {string} from "prop-types";
import useSWR, {SWRConfiguration} from "swr";

const useData = (url: string, config?: SWRConfiguration) => {
    const fetcher = (...args: [ url: string, init: RequestInit ]) => fetch(...args).then(res => res.json());
    const {data, error} = useSWR(url, fetcher, config);

    return {
        data: data,
        isLoading: !data && !error,
        error: error,
    }
}

export default useData;