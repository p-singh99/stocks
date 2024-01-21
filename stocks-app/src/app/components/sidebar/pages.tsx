import { AiOutlineHome } from 'react-icons/ai'
import { BsBriefcase, BsInfoLg } from 'react-icons/bs'
import { MdTrendingUp } from 'react-icons/md'
import { BiDollar } from 'react-icons/bi'
import React from 'react'

export enum Pages {
  home = 'Home',
  portfolio = 'Portfolio',
  popular = 'Popular',
  currency = 'Currency Converter',
  about = 'About',
}

export const PageIcons = {
  [Pages.home]: <AiOutlineHome />,
  [Pages.portfolio]: <BsBriefcase />,
  [Pages.popular]: <MdTrendingUp />,
  [Pages.currency]: <BiDollar />,
  [Pages.about]: <BsInfoLg />
}
