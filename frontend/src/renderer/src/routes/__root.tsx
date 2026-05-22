import { createRootRoute, Outlet, useRouterState } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/react-router-devtools'
import { AppShell } from '@renderer/shared/layouts/app-shell'

export const Route = createRootRoute({
  component: RootComponent
})

/* Routes that render full-bleed without the AppChrome topbar.
   The splash needs the whole window for its cinematic backdrop. */
const CHROMELESS_PATHS = new Set<string>(['/'])

function RootComponent(): React.JSX.Element {
  const pathname = useRouterState({ select: (s) => s.location.pathname })
  const useChrome = !CHROMELESS_PATHS.has(pathname)

  return (
    <>
      {useChrome ? <AppShell /> : <Outlet />}
      {import.meta.env.DEV && <TanStackRouterDevtools position="bottom-right" />}
    </>
  )
}
