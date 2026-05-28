import { useEffect, useState } from 'react'
import { Minus, Settings, Square, X } from 'lucide-react'
import { Link, useRouterState } from '@tanstack/react-router'
import { Button } from '@renderer/shared/ui/button'
import { cn } from '@renderer/shared/utils'
import { IPC } from '@shared/ipc/channels'

const noDragRegion = { WebkitAppRegion: 'no-drag' } as React.CSSProperties

function getPlatform(): NodeJS.Platform | 'web' {
  if (typeof window === 'undefined' || !window.electron) return 'web'
  return window.electron.process.platform as NodeJS.Platform
}

export function WindowControls(): React.JSX.Element | null {
  const pathname = useRouterState({ select: (s) => s.location.pathname })
  const platform = getPlatform()
  const showWindowControls = platform !== 'darwin' && platform !== 'web'
  const settingsActive = pathname === '/settings'

  const [isMaximized, setIsMaximized] = useState(false)

  useEffect(() => {
    if (!showWindowControls) return
    let cancelled = false
    window.api.invoke(IPC.WINDOW.IS_MAXIMIZED).then((r) => {
      if (!cancelled) setIsMaximized(r.isMaximized)
    })
    // Stay in sync when the OS changes the maximize state (snap, double-click…).
    const unsubscribe = window.api.on(IPC.WINDOW.MAXIMIZE_CHANGED, (p) => {
      setIsMaximized(p.isMaximized)
    })
    return () => {
      cancelled = true
      unsubscribe()
    }
  }, [showWindowControls])

  if (!showWindowControls) return null

  return (
    <div
      className="absolute right-2 top-2 z-50 flex items-center gap-0.5"
      style={noDragRegion}
    >
      <Button
        asChild
        variant="ghost"
        size="icon-sm"
        aria-label="Configurações"
        className={cn(settingsActive && 'bg-foreground/8 text-foreground')}
      >
        <Link to="/settings">
          <Settings />
        </Link>
      </Button>
      <Button
        variant="ghost"
        size="icon-xs"
        aria-label="Minimizar"
        onClick={() => void window.api.invoke(IPC.WINDOW.MINIMIZE)}
      >
        <Minus />
      </Button>
      <Button
        variant="ghost"
        size="icon-xs"
        aria-label={isMaximized ? 'Restaurar' : 'Maximizar'}
        onClick={async () => {
          const r = await window.api.invoke(IPC.WINDOW.MAXIMIZE_TOGGLE)
          setIsMaximized(r.isMaximized)
        }}
      >
        <Square />
      </Button>
      <Button
        variant="ghost"
        size="icon-xs"
        aria-label="Fechar"
        className="hover:bg-destructive hover:text-destructive-foreground"
        onClick={() => void window.api.invoke(IPC.WINDOW.CLOSE)}
      >
        <X />
      </Button>
    </div>
  )
}
