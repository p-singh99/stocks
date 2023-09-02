import './globals.css'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import Head from 'next/head'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Stocks App',
  description: 'Keep track of your stocks portfolio',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <Head>
        <meta name="viewport" content="width=device-width,initial-scale=1" />
      </Head>
      <body className={inter.className + " bg-blue text-white"}>{children}</body>
    </html>
  )
}
