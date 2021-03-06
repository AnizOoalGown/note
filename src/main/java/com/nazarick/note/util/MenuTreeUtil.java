package com.nazarick.note.util;

import com.nazarick.note.constants.Constants;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;

import java.util.*;

public class MenuTreeUtil {
    public static List<MenuNode> buildMenuTree(LinkedList<Note> notes) {
        List<MenuNode> menuTree = new ArrayList<>();

        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (Constants.PARENT_ID.equals(note.getParentId())) {
                iterator.remove();
                menuTree.add(new MenuNode(note));
            }
        }

        menuTree.sort(Comparator.comparing(MenuNode::getOrderNo));

        for (MenuNode node : menuTree) {
            buildMenuTreeHelper(node, notes);
        }

        return menuTree;
    }

    private static void buildMenuTreeHelper(MenuNode node, LinkedList<Note> notes) {
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (node.getId().equals(note.getParentId())) {
                iterator.remove();
                node.getChildren().add(new MenuNode(note));
            }
        }

        node.getChildren().sort(Comparator.comparing(MenuNode::getOrderNo));

        for (MenuNode child : node.getChildren()) {
            buildMenuTreeHelper(child, notes);
        }
    }
}
