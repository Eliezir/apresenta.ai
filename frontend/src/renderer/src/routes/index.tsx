import { useMemo } from 'react'
import { ArrowRight, MoonStar, Sun } from 'lucide-react'
import { createFileRoute, useNavigate } from '@tanstack/react-router'
import logoLight from '@renderer/shared/assets/logo-light.png'
import logoDark from '@renderer/shared/assets/logo-dark.png'
import { Button } from '@renderer/shared/ui/button'
import { useTheme } from '@renderer/shared/hooks/use-theme'

export const Route = createFileRoute('/')({
  component: OnboardingPage
})

function OnboardingPage(): React.JSX.Element {
  const { isDark, setIsDark } = useTheme()
  const navigate = useNavigate()

  return (
    <div className="relative isolate flex h-screen items-center justify-center overflow-hidden bg-canvas text-foreground">
      <Backlight />
      <Stars />

      <div className="relative flex flex-col items-center gap-6 text-center">
        <img
          src={isDark ? logoDark : logoLight}
          alt="Apresenta.AI"
          className="size-24 animate-in fade-in zoom-in-95 animation-duration-[500ms] fill-mode-[backwards] [animation-timing-function:var(--ease-out)] drop-shadow-sm"
        />

        <h1 className="animate-in fade-in slide-in-from-bottom-2 animation-duration-[400ms] [animation-delay:120ms] fill-mode-[backwards] font-display text-4xl font-bold tracking-tight">
          Apresenta
          <span
            className="bg-clip-text text-transparent"
            style={{ backgroundImage: 'var(--gradient-ai)' }}
          >
            .AI
          </span>
        </h1>

        <p className="animate-in fade-in animation-duration-[300ms] text-sm text-muted-foreground">
          Tudo pronto.
        </p>

        <div className="mt-2 flex flex-wrap items-center justify-center gap-2 animate-in fade-in animation-duration-[300ms] [animation-delay:700ms] fill-mode-[backwards]">
          <Button size="sm" onClick={() => void navigate({ to: '/home' })}>
            Entrar no app
            <ArrowRight />
          </Button>
          <Button
            variant="secondary"
            size="sm"
            onClick={() => setIsDark((v) => !v)}
            aria-label="Alternar tema"
          >
            {isDark ? <Sun /> : <MoonStar />}
            {isDark ? 'Tema claro' : 'Tema escuro'}
          </Button>
        </div>
      </div>
    </div>
  )
}

function Backlight(): React.JSX.Element {
  return (
    <div
      aria-hidden
      className="pointer-events-none absolute inset-0 -z-10"
      style={{
        background: `
          radial-gradient(ellipse 50% 50% at 30% 38%, oklch(0.46 0.26 295 / 0.42), transparent 70%),
          radial-gradient(ellipse 45% 45% at 72% 55%, oklch(0.62 0.26 300 / 0.32), transparent 70%),
          radial-gradient(ellipse 55% 45% at 50% 92%, oklch(0.80 0.16 290 / 0.30), transparent 70%)
        `,
        filter: 'blur(60px)'
      }}
    />
  )
}

type StarDef = {
  id: number
  top: string
  left: string
  size: number
  delay: number
  duration: number
  low: number
  high: number
  accent: boolean
}

function Stars(): React.JSX.Element {
  const stars = useMemo<StarDef[]>(() => {
    const count = 42
    return Array.from({ length: count }, (_, i) => ({
      id: i,
      top: `${Math.random() * 100}%`,
      left: `${Math.random() * 100}%`,
      size: 1 + Math.random() * 1.8,
      delay: -Math.random() * 5,
      duration: 3 + Math.random() * 4,
      low: 0.05 + Math.random() * 0.15,
      high: 0.4 + Math.random() * 0.4,
      accent: Math.random() < 0.25
    }))
  }, [])

  return (
    <div aria-hidden className="pointer-events-none absolute inset-0 overflow-hidden">
      {stars.map((s) => (
        <span
          key={s.id}
          className="absolute rounded-full"
          style={
            {
              top: s.top,
              left: s.left,
              width: `${s.size}px`,
              height: `${s.size}px`,
              background: s.accent ? 'var(--star-accent)' : 'var(--star-color)',
              animation: `twinkle ${s.duration}s ${s.delay}s ease-in-out infinite`,
              '--twinkle-low': s.low,
              '--twinkle-high': s.high
            } as React.CSSProperties
          }
        />
      ))}
    </div>
  )
}
