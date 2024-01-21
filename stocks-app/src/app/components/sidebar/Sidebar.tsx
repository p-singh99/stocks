import { 
    Button, 
    Drawer, 
    DrawerCloseButton, 
    DrawerContent, 
    DrawerHeader, 
    DrawerOverlay,
    DrawerBody, 
    VStack,
    Divider,
    Stack,
} from "@chakra-ui/react";
import { Pages, PageIcons } from "./pages";

interface SidebarProps {
    isOpen: boolean,
    onClose: () => void,
}

const SidebarContent = () => {

    const sidebarItems = Object.values(Pages);

    return (
        <VStack spacing='1em' height='100%' justifyContent='center' alignItems="left">
            {sidebarItems.map((item) => {
            return (
                <Button w='100%' bg='none' color='white' _hover={{ bg: 'blue.300' }} textAlign={"left"}>
                    {item}
                </Button>
            )})}
        </VStack>   
    )
}

export default function Sidebar({ isOpen, onClose }: SidebarProps) {
    return (
        <Drawer 
            isOpen={isOpen} 
            placement="left" 
            onClose={onClose}
        >
            <DrawerOverlay>
                <DrawerContent bg='blue.700'>
                    <DrawerCloseButton />
                    <DrawerHeader>Stocks</DrawerHeader>
                    <DrawerBody alignItems="baseline">
                        <SidebarContent />
                    </DrawerBody>
                </DrawerContent>
            </DrawerOverlay>
        </Drawer>
    )
}