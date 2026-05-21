import { resolve } from 'path'
import { defineConfig } from 'electron-vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
import { tanstackRouter } from '@tanstack/router-plugin/vite'
import { findFreePort } from './scripts/findFreePort'

export default defineConfig(async () => {
  const port = await findFreePort(5173)

  return {
    main: {},
    preload: {},
    renderer: {
      resolve: {
        alias: {
          '@renderer': resolve('src/renderer/src')
        }
      },
      plugins: [
        tanstackRouter({ target: 'react', autoCodeSplitting: true }),
        react(),
        tailwindcss()
      ],
      server: {
        port,
        strictPort: true
      }
    }
  }
})
