/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
    './src/components/**/*.{js,ts,jsx,tsx,mdx}',
    './src/app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
      colors: {
        'blue': '#6788ff',
        'blue-darker': '#141f45',
        'blue-darkest': '#000517',
        'grey-form': '#8c8c8d',
        'blue-url': '#516bcd',
        'text-input': '#1b1a38',
        'error-red': '#ff0000',
      }
    },
    screens: {

      '2xl': {'max': '1536px'},
      // => @media (max-width: 1536px) { ... }

      '2xl': {'max': '1920px'},
      // => @media (max-width: 1536px) { ... }

      'xl': {'max': '1279px'},
      // => @media (max-width: 1279px) { ... }

      'lg': {'max': '1023px'},
      // => @media (max-width: 1023px) { ... }

      'md': {'max': '767px'},
      // => @media (max-width: 767px) { ... }

      'sm': {'max': '639px'},
      // => @media (max-width: 639px) { ... }
    }
  },
  plugins: [],
}
