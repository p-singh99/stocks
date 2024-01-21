import type User from '../interfaces/User'

export interface UserContextType {
  isLoggedIn: boolean
  user: User
  setUser: (user: User) => any
  setLoggedIn: (isLoggedIn: boolean) => any
}
