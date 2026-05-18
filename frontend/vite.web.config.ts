import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
import { resolve } from 'path'

// Standalone Vite config para rodar o renderer no navegador (sem Electron).
// Útil dentro do devcontainer/Codespaces, onde a janela nativa não pode ser
// aberta. O app final continua sendo Electron — esse modo é só pra DX.
export default defineConfig({
  root: resolve(__dirname, 'src/renderer'),
  resolve: {
    alias: {
      '@renderer': resolve(__dirname, 'src/renderer/src')
    }
  },
  plugins: [react(), tailwindcss()],
  server: {
    host: '0.0.0.0',
    port: 5173,
    strictPort: false
  }
})
