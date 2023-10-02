import { Input, InputLeftElement, InputGroup } from '@chakra-ui/react'
import { SearchIcon } from '@chakra-ui/icons'

export default function Search() {
    return (
        <InputGroup maxWidth='40em'>
            <InputLeftElement pointerEvents='none'>
                <SearchIcon color='gray.300' />
            </InputLeftElement>
            <Input type='search' borderRadius='xl' placeholder='search'  border='1px solid gray'/>
        </InputGroup>
    )
}