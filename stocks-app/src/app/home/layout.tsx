'use client'

import React, { useState } from 'react'

import Header from '../components/header/Header'
import Sidebar from '../components/sidebar/Sidebar'

const HomeLayout = ({
  children
}: {
  children: React.ReactNode
}): React.JSX.Element => {
  const [isSidebarOpen, setSidebarOpen] = useState(false)

  const onSidebarClose = (): void => {
    setSidebarOpen(false)
  }

  return (
        <>
            <Header sidebarToggle={setSidebarOpen}/>
            <Sidebar isOpen={isSidebarOpen} onClose={onSidebarClose} />
            {children}
        </>
  )
}

export default HomeLayout
