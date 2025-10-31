import { useState } from 'react'
import axios from 'axios'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

export function Contact() {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [message, setMessage] = useState('')
  const [status, setStatus] = useState<string | null>(null)

  async function submit(e: React.FormEvent) {
    e.preventDefault()
    setStatus(null)
    try {
      await axios.post(`${API_BASE}/contact`, { name, email, message })
      setStatus('Message sent! I will get back to you shortly.')
      setName(''); setEmail(''); setMessage('')
    } catch (e: any) {
      setStatus(e?.message || 'Failed to send message')
    }
  }

  return (
    <section id="contact" className="max-w-6xl mx-auto px-4 py-16">
      <h2 className="text-3xl font-bold mb-6">Contact</h2>
      <form onSubmit={submit} className="grid gap-4 max-w-xl">
        <input className="p-3 rounded border border-white/10" placeholder="Name" value={name} onChange={e=>setName(e.target.value)} required />
        <input className="p-3 rounded border border-white/10" placeholder="Email" type="email" value={email} onChange={e=>setEmail(e.target.value)} required />
        <textarea className="p-3 rounded border border-white/10" rows={5} placeholder="Your message" value={message} onChange={e=>setMessage(e.target.value)} required />
        <button className="px-5 py-3 rounded bg-brand-600 text-white hover:bg-brand-700 w-fit">Send</button>
        {status && <p className="text-sm opacity-80">{status}</p>}
      </form>
    </section>
  )
}



