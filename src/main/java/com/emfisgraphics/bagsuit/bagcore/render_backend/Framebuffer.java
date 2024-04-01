package com.emfisgraphics.bagsuit.bagcore.render_backend;

import com.emfisgraphics.bagsuit.bagcore.render_frontend.Texture;

import static org.lwjgl.opengl.GL30.*;

/*
----------------------------------------------------------------------------
                        Bag Core - Framebuffer
                     Copyright (C) Márton Bán 2024


----------------------------------------------------------------------------
*/

public class Framebuffer {

    private int fboID = 0;
    private Texture texture = null;

    public Framebuffer(int width, int height) {
        // Generate framebuffer
        fboID = glGenFramebuffers();

        // Create the texture to the data to, and attach it to our framebuffer
        this.texture = new Texture(width, height);

        // Create RenderBuffer to store depth info
        int rboID = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, rboID);
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, width, height);


        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fboID);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, this.texture.getTexID(), 0);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, rboID);

        // Check everything is okay
        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            assert false: "Error: FrameBuffer is not complete";
        }

        glBindFramebuffer(GL_FRAMEBUFFER, 0);

    }

    public void bind() {
        glBindFramebuffer(GL_DRAW_FRAMEBUFFER, fboID);
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }
}