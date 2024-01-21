export const isValidEmail = (email: string): boolean => {
  // eslint-disable-next-line
  const validEmail: RegExp = /([-!#-'*+/-9=?A-Z^-~]+(\.[-!#-'*+/-9=?A-Z^-~]+)*|"([]!#-[^-~ \t]|(\\[\t -~]))+")@([-!#-'*+/-9=?A-Z^-~]+(\.[-!#-'*+/-9=?A-Z^-~]+)*|\[[\t -Z^-~]*])/i
  return validEmail.test(email)
}

export const isEmptyString = (str: string): boolean => {
  const length = str.replace(/\s+/g, '').length
  return length === 0
}

export const isValidName = (name: string): boolean => {
  return name.length >= 2
}

export const isValidPassword = (password: string): boolean => {
  const hasUpperCase: RegExp = /[A-Z]/
  if (
    password.length < 8 ||
          !hasUpperCase.test(password) ||
          !hasUpperCase.test(password)
  ) return false

  return true
}
