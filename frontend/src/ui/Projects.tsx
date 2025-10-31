import { useEffect, useState } from 'react'
import axios from 'axios'

type Project = {
  id: number
  title: string
  description: string
  techStack?: string
  imageURL?: string
  projectURL?: string
}

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

export function Projects() {
  const [projects, setProjects] = useState<Project[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    (async () => {
      try {
        const res = await axios.get(`${API_BASE}/projects`)
        setProjects(res.data)
      } catch (e: any) {
        setError(e?.message || 'Failed to load projects')
      } finally {
        setLoading(false)
      }
    })()
  }, [])

  return (
    <section id="projects" className="max-w-6xl mx-auto px-4 py-16">
      <div className="flex items-end justify-between mb-6">
        <h2 className="text-3xl font-bold">Projects</h2>
        <a className="text-brand-600 hover:underline" href="#contact">Work with me →</a>
      </div>
      {loading && <p>Loading projects…</p>}
      {error && <p className="text-red-500">{error}</p>}
      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {projects.map(p => (
          <article key={p.id} className="border border-white/10 rounded-lg p-4 bg-neutral-50 dark:bg-neutral-900">
            <h3 className="font-semibold text-lg">{p.title}</h3>
            <p className="text-sm text-neutral-600 dark:text-neutral-300 mt-2">{p.description}</p>
            {p.techStack && <p className="mt-2 text-xs opacity-80">{p.techStack}</p>}
            <div className="mt-3 flex gap-3">
              {p.projectURL && <a className="text-brand-600 hover:underline" href={p.projectURL} target="_blank">Live</a>}
            </div>
          </article>
        ))}
      </div>
    </section>
  )
}



