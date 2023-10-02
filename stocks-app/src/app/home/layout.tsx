'use client'

import { useState } from 'react';

import Header from "../components/header/Header";
import Sidebar from '../components/sidebar/Sidebar';


export default function HomeLayout({
    children,
  }: {
    children: React.ReactNode
  }) {

    const [isSidebarOpen, setSidebarOpen] = useState(false);

    const onSidebarClose = () => {
        setSidebarOpen(false);
    }

    return (
        <>
            <Header sidebarToggle={setSidebarOpen}/>
            <Sidebar isOpen={isSidebarOpen} onClose={onSidebarClose} />
            {children}
        </>
    )
  }