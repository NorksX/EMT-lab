package mk.ukim.finki.wp.emtlab.model.listener;

import mk.ukim.finki.wp.emtlab.model.events.HostCreatedEvent;
import mk.ukim.finki.wp.emtlab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshMaterializedView();
    }
}