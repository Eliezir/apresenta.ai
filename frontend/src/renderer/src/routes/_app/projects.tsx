import {
  Calendar,
  Copy,
  MoreHorizontal,
  Pencil,
  Share2,
  Sparkles,
  Trash2
} from 'lucide-react'
import { createFileRoute } from '@tanstack/react-router'
import { Button } from '@renderer/shared/ui/button'
import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle
} from '@renderer/shared/ui/card'
import {
  Popover,
  PopoverContent,
  PopoverHeader,
  PopoverItem,
  PopoverSeparator,
  PopoverTitle,
  PopoverTrigger
} from '@renderer/shared/ui/popover'
import { cn } from '@renderer/shared/utils'

export const Route = createFileRoute('/_app/projects')({
  component: PreviewProjectsPage
})

type Tone = 'primary' | 'secondary' | 'tertiary' | 'quaternary' | 'ai'

const chipBg: Record<Tone, string> = {
  primary: 'bg-primary-soft text-primary-soft-foreground',
  secondary: 'bg-secondary-soft text-secondary-soft-foreground',
  tertiary: 'bg-tertiary-soft text-tertiary-soft-foreground',
  quaternary: 'bg-quaternary-soft text-quaternary-soft-foreground',
  ai: 'text-white'
}

const chipBgStyle: Partial<Record<Tone, React.CSSProperties>> = {
  ai: { background: 'var(--gradient-ai)' }
}

type Project = {
  id: string
  title: string
  subject: string
  snippet: string
  visualModel: string
  modifiedAt: string
  status?: 'generating'
  badgeTone: Tone
}

const projects: Project[] = [
  {
    id: '1',
    title: 'Heurísticas de Nielsen no Spotify',
    subject: 'IHC · Avaliação heurística',
    snippet: 'Aplicação das 10 heurísticas de usabilidade na interface do Spotify Web.',
    visualModel: 'Editorial',
    modifiedAt: 'há 2 horas',
    badgeTone: 'primary'
  },
  {
    id: '2',
    title: 'Design Pattern: Strategy',
    subject: 'POO · Padrões de projeto',
    snippet: 'Comportamento intercambiável encapsulado em famílias de algoritmos.',
    visualModel: 'Minimalist',
    modifiedAt: 'ontem',
    status: 'generating',
    badgeTone: 'quaternary'
  },
  {
    id: '3',
    title: 'Percurso Cognitivo no ADT Studio',
    subject: 'IHC · Inspeção de usabilidade',
    snippet: 'Análise passo a passo do fluxo de criação de novos projetos.',
    visualModel: 'Editorial',
    modifiedAt: 'há 3 dias',
    badgeTone: 'primary'
  },
  {
    id: '4',
    title: 'Algoritmos de Ordenação',
    subject: 'DSA · Análise de complexidade',
    snippet: 'Bubble, Quick, Merge e Radix — quando usar cada um.',
    visualModel: 'Dark Mono',
    modifiedAt: 'há 1 semana',
    badgeTone: 'secondary'
  },
  {
    id: '5',
    title: 'Arquitetura MVC',
    subject: 'POO · Arquitetura de software',
    snippet: 'Separação de responsabilidades em camadas Model, View e Controller.',
    visualModel: 'Default',
    modifiedAt: 'há 2 semanas',
    badgeTone: 'tertiary'
  },
  {
    id: '6',
    title: 'Princípios SOLID',
    subject: 'POO · Boas práticas',
    snippet: 'Cinco diretrizes para um código mais coeso e desacoplado.',
    visualModel: 'Minimalist',
    modifiedAt: 'há 1 mês',
    badgeTone: 'quaternary'
  }
]

function ProjectCard({ project }: { project: Project }): React.JSX.Element {
  return (
    <Card>
      <CardHeader>
        <CardTitle className="line-clamp-1">{project.title}</CardTitle>
        <CardDescription>{project.subject}</CardDescription>
        <CardAction>
          <Popover>
            <PopoverTrigger asChild>
              <Button size="icon-sm" variant="ghost" aria-label="Mais ações">
                <MoreHorizontal />
              </Button>
            </PopoverTrigger>
            <PopoverContent align="end" className="w-64">
              <PopoverHeader>
                <PopoverTitle>{project.title}</PopoverTitle>
              </PopoverHeader>
              <PopoverItem
                icon={<Pencil className="size-4" />}
                title="Renomear"
                caption="Editar o título do projeto"
              />
              <PopoverItem
                icon={<Copy className="size-4" />}
                title="Duplicar"
                caption="Criar uma cópia editável"
              />
              <PopoverItem
                icon={<Share2 className="size-4" />}
                title="Exportar HTML"
                caption="Baixar a última geração"
              />
              <PopoverSeparator />
              <PopoverItem
                icon={<Trash2 className="size-4" />}
                title="Remover"
                caption="Apaga o projeto e o histórico"
                className="text-destructive hover:bg-destructive/10"
                iconClassName="bg-destructive/10 text-destructive"
              />
            </PopoverContent>
          </Popover>
        </CardAction>
      </CardHeader>
      <CardContent>
        <p className="line-clamp-2 text-sm text-muted-foreground">{project.snippet}</p>
      </CardContent>
      <CardFooter className="flex items-center justify-between">
        <div className="flex items-center gap-1.5 text-xs text-muted-foreground">
          <Calendar className="size-3.5" />
          {project.modifiedAt}
        </div>
        {project.status === 'generating' ? (
          <span className="flex items-center gap-1.5 rounded-full bg-tertiary-soft px-2 py-0.5 text-xs font-semibold text-tertiary-soft-foreground">
            <span className="size-1.5 animate-pulse rounded-full bg-tertiary-soft-foreground" />
            Gerando…
          </span>
        ) : (
          <span
            className={cn('rounded-full px-2 py-0.5 text-xs font-semibold', chipBg[project.badgeTone])}
            style={chipBgStyle[project.badgeTone]}
          >
            {project.visualModel}
          </span>
        )}
      </CardFooter>
    </Card>
  )
}

function GenerateCta(): React.JSX.Element {
  return (
    <div className="ai-backlight relative">
      <Card className="overflow-hidden">
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <span
              className="grid size-7 place-items-center rounded-md text-white shadow-sm"
              style={{ background: 'var(--gradient-ai)' }}
            >
              <Sparkles className="size-3.5" />
            </span>
            Geração rápida
          </CardTitle>
          <CardDescription>Cole seu material e veja a IA estruturar tudo em Markdown.</CardDescription>
        </CardHeader>
        <CardFooter>
          <Button size="sm">Começar</Button>
        </CardFooter>
      </Card>
    </div>
  )
}

function PreviewProjectsPage(): React.JSX.Element {
  return (
    <main className="flex-1 overflow-y-auto px-6 py-6">
      <header className="mb-6 flex items-baseline justify-between gap-4">
        <div className="flex items-baseline gap-3">
          <h1 className="font-display text-2xl font-bold tracking-tight">Projetos</h1>
          <span className="text-sm text-muted-foreground">6 projetos</span>
        </div>
      </header>
      <div className="grid gap-5 sm:grid-cols-2 lg:grid-cols-3">
        <GenerateCta />
        {projects.map((p) => (
          <ProjectCard key={p.id} project={p} />
        ))}
      </div>
    </main>
  )
}
