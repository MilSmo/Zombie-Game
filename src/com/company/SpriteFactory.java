package com.company;

import java.io.IOException;

public interface SpriteFactory {
    Sprite newSprite(int x,int y) throws IOException;
}