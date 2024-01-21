import { useContext } from "react"
import { 
    IconButton, 
    Flex, 
    Heading, 
    Spacer, 
    HStack, 
    Avatar, 
    Menu, 
    MenuButton,
    MenuList,
    MenuItem,
    VStack, 
    Text,
    Box,
} from "@chakra-ui/react"
import { BellIcon, HamburgerIcon } from "@chakra-ui/icons"
import { FiChevronDown } from "react-icons/fi"
import Search from "../Search"
import { ProfileItems } from "./profileItems"
import { UserContext } from "../../context/userContext"

interface HeaderProps {
    sidebarToggle: Function,
}

export default function Header({ sidebarToggle }: HeaderProps) {

    const userContext = useContext(UserContext);

    const profileItems = Object.values(ProfileItems);
    console.log(profileItems);

    return (
        <Flex as='nav' p='0.5em' alignItems='center' bg='#000517' gap='1em'>
            <IconButton 
                size='md' 
                aria-label='open menu' 
                icon={<HamburgerIcon/>} 
                colorScheme="white"
                _hover={{ bg: 'gray.900' }} 
                borderRadius='xl'
                onClick={() => {sidebarToggle(true)}}
            />
            <Heading size='md'>Page Title</Heading>

            <Spacer/>
            <Search/>
            <Spacer/>

            <HStack>
                <IconButton 
                    size='md' 
                    aria-label='open menu' 
                    icon={<BellIcon/>} 
                    colorScheme="white"
                    _hover={{ bg: 'gray.900' }} 
                    borderRadius='xl'
                />
                <Menu>
                    <MenuButton _hover={{ bg: 'gray.900' }} borderRadius='xl'>
                        <HStack>
                            <Avatar size='sm' />
                                <VStack
                                    display={{ base: 'none', md: 'flex' }}
                                    alignItems="flex-start"
                                    spacing="1px"
                                    ml="2">
                                    <Text fontSize="sm">{userContext?.user.firstName} {userContext?.user.lastName}</Text>
                                </VStack>
                                <Box display={{ base: 'none', md: 'flex' }}>
                                    <FiChevronDown />
                                </Box>
                        </HStack>
                    </MenuButton>
                    <MenuList
                        bg='blue.700'
                        border='none'
                        borderColor='none'
                        borderRadius='xl'
                        >
                        {profileItems.map((value, index) => {
                            return(
                                <MenuItem 
                                    key={value} 
                                    bg='bg.700'
                                    _hover={{ bg: 'blue.300' }} >
                                        {value}
                                </MenuItem>)
                            })}
                    </MenuList>
                </Menu>
            </HStack>
        </Flex>
    )
}