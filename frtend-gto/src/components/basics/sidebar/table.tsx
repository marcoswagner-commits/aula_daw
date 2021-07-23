import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as FiIcons from 'react-icons/fi';
import * as BsIcons from 'react-icons/bs';
import * as BiIcons from 'react-icons/bi';

export const SidebarData = [
  {
    title: 'Home',
    path: '/',
    icon: <AiIcons.AiFillHome />,
    cName: 'nav-text'
  },
  {
    title: 'DashBoard',
    path: '/dashboard',
    icon: <IoIcons.IoIosPaper />,
    cName: 'nav-text'
  },
  {
    title: 'Proprietários',
    path: '/proprietarios',
    icon: <FiIcons.FiUsers />,
    cName: 'nav-text'
  },
  {
    title: 'Obras',
    path: '/obras',
    icon: <BsIcons.BsBuilding />,
    cName: 'nav-text'
  },
  {
    title: 'Itens',
    path: '/itens',
    icon: <BiIcons.BiSitemap />,
    cName: 'nav-text'
  },
  {
    title: 'Subitens',
    path: '/subitens',
    icon: <FaIcons.FaSitemap />,
    cName: 'nav-text'
  },
  {
    title: 'Lançamentos',
    path: '/lancamentos',
    icon: <FaIcons.FaStackOverflow />,
    cName: 'nav-text'
  }
];
