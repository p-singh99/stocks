'use client'

import React, { useContext, useEffect } from 'react'

import { UserContext } from '../context/userContext'
import { useCookies } from 'react-cookie'

const Home = (): React.JSX.Element => {
  const userContext = useContext(UserContext)
  const [cookies] = useCookies(['user'])

  useEffect(() => {
    console.log(`Cookie: ${cookies.user}`)
  }, [])

  return (
        <>
            {
              <h1>Welcome {userContext?.user.firstName}</h1> ?? <h1>Please login to continue</h1>
            }

        </>
  )
}

export default Home
