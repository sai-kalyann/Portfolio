import { useEffect, useState } from 'react'
import { Projects } from './Projects'
import { Contact } from './Contact'
import { Resume } from './Resume'

function Nav() {
  const [open, setOpen] = useState(false)
  return (
    <header className="sticky top-0 z-50 bg-white/70 dark:bg-black/30 backdrop-blur border-b border-white/10">
      <div className="max-w-6xl mx-auto px-4 py-3 flex items-center justify-between">
        <a href="#home" className="font-bold text-xl">{`<`}Sai Kalyan{`/>`}</a>
        <button className="md:hidden" onClick={() => setOpen(v => !v)} aria-label="Menu">
          ☰
        </button>
        <nav className={`md:flex gap-6 ${open ? 'block' : 'hidden'} md:block`}>
          <a href="#home" className="hover:text-brand-500">Home</a>
          <a href="#about" className="hover:text-brand-500">About</a>
          <a href="#projects" className="hover:text-brand-500">Projects</a>
          <a href="#contact" className="hover:text-brand-500">Contact</a>
          <a href="#resume" className="hover:text-brand-500">Resume</a>
        </nav>
      </div>
    </header>
  )
}

function Hero() {
  return (
    <section id="home" className="max-w-6xl mx-auto px-4 py-20 grid md:grid-cols-2 gap-8 items-center">
      <div className="fade-in">
        <h1 className="text-4xl md:text-6xl font-extrabold leading-tight">Engineer. Builder. Problem Solver.</h1>
        <p className="mt-4 text-lg text-neutral-600 dark:text-neutral-300">I craft reliable microservices, secure APIs, and delightful UIs. Let’s build something exceptional.</p>
        <div className="mt-6 flex gap-3">
          <a href="#projects" className="px-5 py-3 rounded bg-brand-600 text-white hover:bg-brand-700">View Projects</a>
          <a href="#contact" className="px-5 py-3 rounded border border-neutral-300 dark:border-neutral-700 hover:bg-neutral-50 dark:hover:bg-neutral-800">Contact Me</a>
        </div>
      </div>
      <div className="fade-in">
        <div className="aspect-square rounded-2xl bg-gradient-to-br from-brand-500/20 to-purple-500/20 border border-white/10 flex items-center justify-center text-7xl">🚀</div>
      </div>
    </section>
  )
}

function About() {
  return (
    <section id="about" className="max-w-6xl mx-auto px-4 py-16">
      <h2 className="text-3xl font-bold mb-6">About Me</h2>
      <p className="text-neutral-700 dark:text-neutral-300 leading-relaxed">
        I’m a full‑stack developer specializing in Java Spring Boot microservices and modern React frontends.
        I value clarity, reliability, and shipping production‑ready features.
      </p>
      <ul className="mt-4 grid sm:grid-cols-2 gap-3 text-sm">
        <li className="p-3 rounded bg-neutral-50 dark:bg-neutral-900 border border-white/10">Spring Boot, Spring Cloud, JPA</li>
        <li className="p-3 rounded bg-neutral-50 dark:bg-neutral-900 border border-white/10">Postgres, MySQL, Redis</li>
        <li className="p-3 rounded bg-neutral-50 dark:bg-neutral-900 border border-white/10">React, TypeScript, Tailwind</li>
        <li className="p-3 rounded bg-neutral-50 dark:bg-neutral-900 border border-white/10">Docker, Compose, CI/CD</li>
      </ul>
    </section>
  )
}

export function App() {
  return (
    <div className="min-h-dvh bg-white text-black dark:bg-black dark:text-white">
      <Nav />
      <Hero />
      <About />
      <Projects />
      <Contact />
      <Resume />
      <footer className="mt-16 py-10 text-center text-sm text-neutral-500">© {new Date().getFullYear()} Sai Kalyan. All rights reserved.</footer>
    </div>
  )
}



