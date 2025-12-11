import { 
    CalendarIcon,
    ChartBarIcon,
    ChatBubbleLeftRightIcon,
    DocumentIcon,
    EnvelopeIcon,
    HomeModernIcon, 
    MusicalNoteIcon, 
    RectangleStackIcon, 
    ShoppingBagIcon, 
    SparklesIcon
} from "../icons";

export const MENU_HOME=[
    {label:"Modern",icon:HomeModernIcon,active:true},
    {label:"eCommerce",icon:ShoppingBagIcon},
    {label:"Music",icon:MusicalNoteIcon},
    {label:"General",icon:ChartBarIcon},
    { label: "Front Pages", icon: DocumentIcon, dropdown: true },

]

export const MENU_APPS = [
    { label: "AI", icon: SparklesIcon },
    { label: "Calendar", icon: CalendarIcon },
    { label: "Kanban", icon: RectangleStackIcon },
    { label: "Chats", icon: ChatBubbleLeftRightIcon },
    { label: "Email", icon: EnvelopeIcon },
  ];
  