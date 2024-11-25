

export const fetchJobs = async () => {
    let jobs = []
    try {
        let response = await fetch("/jobs");
        response = await response.json();
        jobs = response.jobs
    } catch (error) {
        console.error("Error fetching data:", error);
    } finally {
        return jobs
    }
};

export const fetchJob = async (id) => {
    if (id === null) {
        console.error('No Id was provided for the job.')
        return;
    }

    let job = {}
    try {
        let response = await fetch("/jobs/"+id)
        response = await response.json();
        job = response
    } catch (error) {
        console.error("Error fetching data:", error);
    } finally {
        return job
    }

}

export const postJob = async (job) => {

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(job)
    }

    let response = await fetch('/jobs', requestOptions)
    response = await response.json()
}

export const loadJobData = async ({params}) => {
    let job = fetchJob(params.id);
    return job;
};

export const loadJobs = async() => {
    let jobs = fetchJobs()
    return jobs
}