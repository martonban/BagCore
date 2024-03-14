package com.emfisgraphics.bagsuit.bagcore.render_backend;

import com.emfisgraphics.bagsuit.bagcore.render_frontend.Texture;

import static org.lwjgl.opengl.GL30.*;

public class Framebuffer {

    private int fboID = 0;
    private Texture texture = null;

    public Framebuffer(int width, int height) {
        // Generate Framebuffer and bind it
        fboID = glGenFramebuffers();
        // Every operation after that is going to effect this framebuffer
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);

        // Creating Texture to attach the rendering result to it (it's an ""empty"" texture)
        this.texture = new Texture(width, height);

        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture.getTexID(), 0);

        // Create RenderBuffer to store depth info
        int rboID = glGenRenderbuffers();
        glBindRenderbuffer(GL_RENDERBUFFER, rboID);
        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT32, width, height);
        glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, rboID);

        // The framebuffer will be ready when we attach at least one buffer to it. Like color, depth etc.
        // Every attachment must be complete as well
        if(glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            assert false: "Error framebuffer is not ready";
        }

        // Bind the framebuffer to the framebuffer target as the default framebuffer
        glBindFramebuffer(fboID, 0);
        glDeleteFramebuffers(fboID);
    }

    public void deleteFramebuffer() {
        glDeleteFramebuffers(fboID);
    }

    public void bind() {
        glBindFramebuffer(GL_FRAMEBUFFER, fboID);
    }

    public void unbind() {
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public int getFboID() {
        return fboID;
    }

}