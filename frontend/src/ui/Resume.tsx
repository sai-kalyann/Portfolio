import axios from 'axios'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

export function Resume() {
  async function download() {
    const url = `${API_BASE}/resume/download`
    const a = document.createElement('a')
    a.href = url
    a.download = 'resume.pdf'
    document.body.appendChild(a)
    a.click()
    a.remove()
  }
  return (
    <section id="resume" className="max-w-6xl mx-auto px-4 py-16">
      <h2 className="text-3xl font-bold mb-4">Resume</h2>
      <p className="text-neutral-600 dark:text-neutral-300 mb-4">Download my resume and let’s talk about how I can help your team ship faster.</p>
      <button onClick={download} className="px-5 py-3 rounded bg-brand-600 text-white hover:bg-brand-700">Download Resume</button>
    </section>
  )
}



