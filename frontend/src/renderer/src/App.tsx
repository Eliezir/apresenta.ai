import { useEffect, useState } from 'react'
import { MoonStar, Sun } from 'lucide-react'
import logoLight from '@renderer/assets/logo-light.png'
import logoDark from '@renderer/assets/logo-dark.png'
import { Button } from '@renderer/components/ui/button'

function App(): React.JSX.Element {
  const [isDark, setIsDark] = useState(false)

  useEffect(() => {
    document.documentElement.classList.toggle('dark', isDark)
  }, [isDark])

  return (
    <div className="flex h-screen flex-col items-center justify-center gap-8 bg-canvas text-foreground">
      <img
        src={isDark ? logoDark : logoLight}
        alt="Apresenta.AI"
        className="size-24 drop-shadow-sm"
      />
      <div className="text-center">
        <h1 className="font-display text-4xl font-bold tracking-tight">
          Apresenta
          <span
            className="bg-clip-text text-transparent"
            style={{ backgroundImage: 'var(--gradient-ai)' }}
          >
            .AI
          </span>
        </h1>
        <p className="mt-2 text-sm text-muted-foreground">Frontend pronto.</p>
      </div>
      <Button variant="secondary" size="sm" onClick={() => setIsDark((v) => !v)}>
        {isDark ? <Sun /> : <MoonStar />}
        {isDark ? 'Tema claro' : 'Tema escuro'}
      </Button>
    </div>
  )
}

export default App
