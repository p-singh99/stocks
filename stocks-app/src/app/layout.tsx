'use client'

import React from 'react'
import './globals.css'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import Head from 'next/head'
import UserProvider from './context/userContext'
import { ChakraProvider, extendTheme } from '@chakra-ui/react'
import { CookiesProvider } from 'react-cookie'

const inter = Inter({ subsets: ['latin'] })

const metadata: Metadata = {
  title: 'Stocks App',
  description: 'Keep track of your stocks portfolio'
}

const RootLayout = ({
  children
}: {
  children: React.ReactNode
}): React.JSX.Element => {
  const theme = extendTheme({
    colors: {
      blue: {
        300: '#6788ff',
        700: '#141f45',
        900: '#141f45'
      }
    }
  })

  return (
    <html lang="en">
      <Head>
        <meta name="viewport" content="width=device-width,initial-scale=1" />
      </Head>
      <body className={inter.className + ' bg-blue-darkest text-white'}>
        <ChakraProvider theme={theme}>
          <CookiesProvider>
            <UserProvider>
              {children}
            </UserProvider>
          </CookiesProvider>
        </ChakraProvider>
      </body>
    </html>
  )
}

export default RootLayout
