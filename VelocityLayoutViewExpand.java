package com.hengtian.framework.velocity;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

public class VelocityLayoutViewExpand extends VelocityLayoutView
{
    @Override
    protected Context createVelocityContext(Map model, HttpServletRequest request, HttpServletResponse response)
                                                                                                                throws IllegalStateException,
                                                                                                                IOException
    {
        ViewToolContext context = new ViewToolContext(getVelocityEngine(), request, response, getServletContext());

        context.putAll(model);

        if (getToolboxConfigLocation() != null)
        {
            XmlFactoryConfiguration cfg = new XmlFactoryConfiguration();

            cfg.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(getToolboxConfigLocation()));

            ToolboxFactory factory = cfg.createFactory();

            context.addToolbox(factory.createToolbox("application"));
            context.addToolbox(factory.createToolbox("request"));
            context.addToolbox(factory.createToolbox("session"));
        }
        return context;
    }
}
