import express from 'express'
import path from 'path'
import url from 'url'
import jobsRouters from './routers/jobsRouters.js'

const PORT = process.env.PORT || 5001
const app = express();

app.use(express.json())

// Get current path because of type module
const __filename = url.fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

app.use('/jobs', jobsRouters)

app.listen(PORT, () => {
    console.log(`Server is running on PORT: ${PORT}`)
})