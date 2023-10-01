export default class Validator {

    static validEmail: RegExp =  /([-!#-'*+/-9=?A-Z^-~]+(\.[-!#-'*+/-9=?A-Z^-~]+)*|"([]!#-[^-~ \t]|(\\[\t -~]))+")@([-!#-'*+/-9=?A-Z^-~]+(\.[-!#-'*+/-9=?A-Z^-~]+)*|\[[\t -Z^-~]*])/i;
    static hasUpperCase: RegExp = /[A-Z]/;
    static hasNumber: RegExp = /\d/;

    static isValidEmail = (email: string): boolean => {
        return this.validEmail.test(email);
    }

    static isEmptyString = (str: string): boolean => {
        const length = str.replace(/\s+/g, '').length;
        return length === 0;
    }

    static isValidName = (name: string): boolean => {
        return name.length >= 2;
    }

    static isValidPassword = (password: string): boolean => {
        if (
            password.length < 8 ||
            !this.hasUpperCase.test(password) ||
            !this.hasUpperCase.test(password)
        ) return false;
        
        return true;
    }

}