import { createFileRoute, Outlet } from '@tanstack/react-router'
import { Sidebar } from '@renderer/shared/layouts/sidebar'
import { WindowControls } from '@renderer/shared/layouts/window-controls'
import { useTheme } from '@renderer/shared/hooks/use-theme'

const dragRegion = { WebkitAppRegion: 'drag' } as React.CSSProperties
const noDragRegion = { WebkitAppRegion: 'no-drag' } as React.CSSProperties

export const Route = createFileRoute('/_app')({
  component: AppLayout
})

function AppLayout(): React.JSX.Element {
  // ⌘⇧D / Ctrl+Shift+D toggles theme across the app shell.
  useTheme({ keyboardShortcut: true })

  return (
    <div className="relative flex h-screen bg-canvas">
      <Sidebar />

      <main className="flex flex-1 overflow-hidden py-3 pr-3 pl-3" style={dragRegion}>
        <div
          className="flex flex-1 overflow-hidden rounded-[14px] border border-border/40 bg-background"
          style={noDragRegion}
        >
          <Outlet />
        </div>
      </main>

      <WindowControls />
    </div>
  )
}
