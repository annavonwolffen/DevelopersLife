package com.annevonwolffen.tfs.developerslife.presentation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.annevonwolffen.tfs.developerslife.presentation.BtnAction.REPEAT;
import static com.annevonwolffen.tfs.developerslife.presentation.BtnAction.NEXT;
import static com.annevonwolffen.tfs.developerslife.presentation.BtnAction.PREVIOUS;

/**
 * Константы с типами действий кнопок
 *
 * @author Terekhova Anna
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({
        PREVIOUS,
        NEXT,
        REPEAT
})
public @interface BtnAction {
    String PREVIOUS = "previous";
    String NEXT = "next";
    String REPEAT = "repeat";
}
