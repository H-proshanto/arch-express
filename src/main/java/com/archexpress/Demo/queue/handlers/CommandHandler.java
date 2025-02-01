package com.archexpress.Demo.queue.handlers;

import com.archexpress.Demo.queue.comnnads.Command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}