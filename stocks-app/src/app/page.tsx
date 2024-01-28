'use client'

import React, { useEffect, useContext } from 'react'
import axios from 'axios'
import { StatusCodes } from 'http-status-codes'
import config from './config.json'
import { useRouter } from 'next/navigation'
import { UserContext } from './context/userContext'
import { type UserAuthenticationResponse } from './interfaces/UserAuthenticationResponse'

const Home = (): React.JSX.Element => {
  const { push } = useRouter()
  const userContext = useContext(UserContext)

  useEffect(() => {
    validateSession()
  }, [])

  const validateSession = async (): Promise<void> => {
    await axios
      .post(
        config['auth-server-base'] + config['validate-session'],
        {},
        {
          headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
          },
          withCredentials: true
        }
      ).then((response) => {
        if (response.status === StatusCodes.OK) {
          const responseData: UserAuthenticationResponse = response.data
          userContext?.setUser({
            id: Number(responseData.id),
            firstName: responseData.firstName,
            lastName: responseData.lastName,
            email: responseData.email
          })
          userContext?.setLoggedIn(true)
          push('/home')
        }
      }).catch(() => {
        push('/login')
      })
  }

  return (
    <></>
  )
}

export default Home
